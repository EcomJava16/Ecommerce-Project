import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Modal({ closeModal, logged }) {
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
            localStorage.setItem("token", res.data.content);
            logged(true);
            closeModal(false)
        }).catch(err => {
            setErrMessage(err.response.data.error);
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
            <div className="modalBackground" >
                <div className="modalContainer">
                    <div className="title">
                        <h1>Login</h1>
                    </div>
                    <div className='title' style={{color:"red",marginBottom:"10px"}}>
                    <h2><a href="http://localhost:8080/oauth2/authorize/google?redirec_uri=http://localhost:3000/oauth2/redirect">Login with Google</a></h2>
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
                </div>
            </div>
        </div>
    )
}