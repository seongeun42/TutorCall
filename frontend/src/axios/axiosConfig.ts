import axios, { Axios } from 'axios'

export const instance: Axios = axios.create({
  withCredentials: true,
  headers: {
    'Content-Type': 'application/json;charset=utf-8'
  }
})
