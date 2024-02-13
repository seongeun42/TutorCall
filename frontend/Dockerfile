FROM node:lts
RUN apt update && apt upgrade -y
RUN apt install npm -y
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build
