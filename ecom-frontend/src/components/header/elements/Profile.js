import React, { useEffect, useState } from 'react';
import axios from 'axios';

export default function Profile({logged}) {
    const [user, setUser] = useState("");

    const logout = () => {
        localStorage.removeItem("token");
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
            setUser(res.data.content.fullName)
            console.log(res);
        }).catch(err => {
            console.log(err);
            logout();
        });
    }

    return (
        <div>
            <div>{user}</div>
            <button type='button' onClick={logout}>Logout</button>
        </div>
    )
}