import React from 'react';
/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import { Link } from 'react-router-dom';

const SLayout = css`
    display: flex;
    justify-content:  space-around;
    align-items: center;
    border: 1px solid #dbdbdb;
    height: 70px;
    width: 100%;
`;


function MainHeader(props) {
    return (
        <div css={SLayout}>
            <Link to="/"> 메인 </Link>
            <Link to="/signin"> 로그인 </Link>
            <Link to="/signup"> 회원가입 </Link>
        </div>
    );
}

export default MainHeader;
