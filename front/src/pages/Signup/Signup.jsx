import React, { useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import MainLayout from '../../components/MainLayout';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


const SInputLayout = css`
    margin-bottom: 15px;
    width: 80%;
    height: 40px;

    & > input{
        width: 100% ;
        height: 100%;
        text-align: center;
    }
`;


function Signup(props) {
    const navigate = useNavigate();

    const [ signupUser, setSignupUser ] = useState({
        username: "",
        password: "",
        name: "",
        email: ""
    });

    const handleInputChange = async(e) => {
        setSignupUser({
            ...signupUser,
            [e.target.name]: e.target.value
        })
        
    }

    console.log(signupUser)
    

    const handleSubmitClick = () => {
        //회원가입 요청
        const option = {
            params: {
                username: signupUser.username
            }
        }

        const signup = async () => {
            // await 비동기를 동기로 바꿔주고 , 갔다와서 사용할수있게끔
            let response = await axios.get("http://localhost:8080/servlet_study_geunhae/auth/signup/duplicate/username", option);
                
            if (response.data) {
                alert("중복된 아이디입니다");
                return;
            }
                // 위에서 중복확인 끝남 
                //중복이 아닐시 실행됨. 
                
            try {
            response = await axios.post("http://localhost:8080/servlet_study_geunhae/auth/signup", signupUser);
                
                if (!response.data) {
                    throw new Error(response);
                }
                alert("회원가입성공");
                navigate("/signin");

            } catch (error) {
                console.log(error);
            }
            
            //리턴이 꼭 있어하는건 아니라서 생략가능함
            return response;

        };
        signup();

        /*
        async 로 만들어보기 
                                프라미스 
        const dublicateUsername = async () => {
            const response = await axios.get("http://localhost:8080/servlet_study_geunhae/auth/signup/duplicate/username", option);

            return response;
        }; 
        프라미스는 프라미스를 리턴하게되어있음 
        dublicateUsername().then((response) => {

        });
        */    // 안에 순서가 상관없을때는 위아래 똑같음 

        /*
        //axios > 요청을 보냄
        axios.get("http://localhost:8080/servlet_study_geunhae/auth/signup/duplicate/username", option)
        // then 가로안에 함수가 실행이됨
        .then((response) => {
            console.log(response);
            const isDuplicateUSername = response.data;
            //response data가 true > 아이디가 중복되었다.
            if (isDuplicateUSername) {


                //response data가 false > 사용가능한아이디.
            } else {
                
            }
        }).catch((error) => {

        })
        */
    };




    return (
        <>
            
            <h1>회원가입</h1>

            <div css={SInputLayout}>
                <input type="text" name='username'placeholder='username' onChange={handleInputChange} />
            </div>
            <div css={SInputLayout}>
                <input type="password" name='password' placeholder='password'onChange={handleInputChange} />
            </div>
            <div css={SInputLayout}>
                <input type="text" name='name' placeholder='name'onChange={handleInputChange} />
            </div>
            <div css={SInputLayout}>
                <input type="text" name='email' placeholder='email'onChange={handleInputChange} />
            </div>
            <button onClick={handleSubmitClick}>가입하기</button>
        </>
    );
}

export default Signup;