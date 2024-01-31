import axios from 'axios';

export const instance = axios.create({
    withCredentials: true,
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    }
});
