import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useDispatch } from 'react-redux';
import { addToken } from '../../redux/actions/userAction'

export default function Modal({ closeModal, logged, openRegister, openForgotPassword }) {
    const dispatch = useDispatch();
    const [loginInfo, setLoginInfo] = useState({
        username: "",
        password: ""
    });
    const [errMessage, setErrMessage] = useState("");

    const setParams = (event) => {
        let previousState = loginInfo;
        setLoginInfo({ ...previousState, [event.target.name]: event.target.value });
    }
    const errorMessage = (errMessage != "" && <span className='errMessage'>{errMessage}</span>)

    const RegisterClickHandler = () => {
        closeModal(false);
        openRegister(true);
    }

    const ForgotPasswordClickHandler = () => {
        closeModal(false);
        openForgotPassword(true);
    }

    const login = (event) => {
        event.preventDefault();
        if (loginInfo.username.length < 6) {
            return (setErrMessage("Username must be from 6 characters"))
        }
        if (loginInfo.password.length < 6) {
            return (setErrMessage("Password must be from 6 characters"))
        }
        axios({
            method: 'post',
            url: 'http://localhost:8080/api/v1/login',
            data: loginInfo
        }).then(res => {
            dispatch(addToken(res.data.content))
            dispatch(addUser('adminn'))
            logged(true);
            const array = res.data.content.roles;
            console.log(array);
            closeModal(false)
        }).catch(err => {
            console.log(err)
            setErrMessage(err.response.data);
        });

    }
    useEffect(() => {
        function handlerClose(event) {
            if (event.which === 27) {
                closeModal(false)
            }
        }

        document.addEventListener("keyup", handlerClose);
        return () => {
            document.removeEventListener("keyup", handlerClose);
        }
    }, [])
    return (
        <div >
            <div className="loginModalBackground" >
                <div className="loginModalContainer">
                    <div className="title">
                        <h1>Login</h1>
                    </div>
                    <form className="body">
                        <div>
                            <label>Username</label>
                            <input type='text' name='username' id='username' onChange={setParams}></input><br />
                        </div>
                        <div>
                            <label>Password</label>
                            <input type='password' name='password' id='password' onChange={setParams}></input>
                        </div>
                        {errorMessage}
                        <div className="footer">
                            <button type='submit' className="btnLogin" onClick={login}>Login</button>
                            <button className="btnCancel" onClick={() => closeModal(false)}>Cancel</button>
                        </div>
                    </form>
                    <div>
                        <span><a onClick={ForgotPasswordClickHandler}>Forgot password ?</a></span>
                        <br />
                        <span>Looking to <a onClick={RegisterClickHandler}>Create an account</a> ?</span>
                    </div>
                </div>
            </div>
        </div>
    )
}