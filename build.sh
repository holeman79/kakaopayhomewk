#!/bin/sh

cd frontend/kakaopayhw/
npm install
npm run build

cd -
mkdir -p src/main/resources/static
cp -rf frontend/kakaopayhw/build/* src/main/resources/static/



