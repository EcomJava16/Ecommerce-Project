import React, { useState } from 'react';
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

    const login = () => {
        axios({
            method: 'post',
            url: 'http://localhost:8080/api/v1/login',
            data: {
                username: loginInfo.username,
                password: loginInfo.password
            }
        }).then(res => {
            console.log(res);
            localStorage.setItem("token", res.data.content);
            localStorage.setItem("username", loginInfo.username);
            logged(true);
            closeModal(false)
        }).catch(err => {
            console.log(err.response.data.error);
            setErrMessage("");
            err.response.data.error.forEach(element => {
                setErrMessage(errMessage + element);
            });
        });

    }

    return (
        <div>
            <div className="modalBackground">
                <div className="modalContainer">
                    <div className="title">
                        <h1>Login</h1>
                    </div>
                    <div className="body">
                        <div>
                            <label htmlFor='username'>Username</label>
                            <input type='text' name='username' id='username' onChange={setParams}></input>
                        </div>
                        <div>
                            <label htmlFor='password'>Password</label>
                            <input type='password' name='password' id='password' onChange={setParams}></input>
                        </div>
                        {errMessage != "" && <div className='errMessage'>{errMessage}</div>}
                    </div>
                    <div className="footer">
                        <button className="btnLogin" onClick={login}>Login</button>
                        <button className="btnCancel" onClick={() => closeModal(false)}>Cancel</button>
                    </div>
                </div>
            </div>
        </div>
    )
}