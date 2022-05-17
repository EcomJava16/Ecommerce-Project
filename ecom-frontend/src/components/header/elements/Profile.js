import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Profile({ logged }) {
    const [user, setUser] = useState({
        username: "",
        firstName: ""
    });

    const logout = () => {
        localStorage.removeItem("token");
        localStorage.removeItem("username");
        logged(false)
    }

    useEffect(() => {
        loadData();
    }, [])

    const loadData = () => {
        axios({
            method: 'get',
            url: 'http://localhost:8080/api/v1/user/' + localStorage.getItem("username"),
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem("token"),
                'Content-Type': 'application/json'
            }
        }).then(res => {
            setUser({ username: res.data.content.username, firstName: res.data.content.firstName })
            console.log(res);
        }).catch(err => {
            console.log(err);
            logout();
        });
    }

    return (
        <div className='dropdown'>
            <button className='btn-dropdown'>Hi {user.firstName}</button>
            <div className='dropdown-item'>
                <a onClick={logout}>Logout</a>
            </div>
        </div>
    )
}