import { click } from '@testing-library/user-event/dist/click';
import React from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

const Btn = css`
    width: 70px;
    height: 70px;
    margin: 10px;
    cursor: pointer;
`;


//콜백 : callback 함수란 비동기 처리 안에서 내가 지정한 순서대로 함수 호출하게 하는 것
function Asynchronous(props) {

    let num = 0;

    const handleClick1 = () => {
        num++;

        const click1 = () => {
            console.log(`${num} click 1 !!`);
        }
        
        const click1After = () => {
            console.log("click 1 실행 후에 실행 ! ");
        }
        const click2 = () => {
            console.log(`${num} click 2 !!`);
        }
        const click2After = () => {
            console.log("click 2 실행 후에 실행 ! ");
        }

        const clickfx = (fx1, fx2) => {
            fx1(num);
            fx2();
        }


        //콜백 함수
            //비동기 처리 함수 setTimeout()
        setTimeout(clickfx, Math.random(3)*1000, click1 ,click1After);
        setTimeout(clickfx, Math.random(3)*1000, click2 ,click2After);
    };
    

    const handleClick2 = () => {
        //promise 기본 형태   Promise((resolve, reject) => {))
        const p1 = new Promise((resolve, reject) => {
            const num = Math.random(4);
            if (Math.round(num % 2, 0) === 0) {
                resolve("짝수");
            } else {
                reject(new Error("홀수"));
            }
            resolve("프로미스 실행 !!");
        });

        //then이 있어야 실행됨 
        p1
        .then(result => {
            console.log(result);
            return "첫번째 then의 리턴"
        })
        .then(result => {
            console.log(result);
        })
        .catch(error => {
            console.log(error);
        })
    }
    
    const handleClick3 = () => {

        //프로미스로 작성한것
        const printUser2 = () => {
            return new Promise((resolve, reject) => {
                resolve("유저2");
                reject(new Error("오류2"));
            });
        }
        
        printUser2().then(r => console.log(r));
        
        //람다 사용시 함수 안에 함수 정의 가능
        const printUser = async () => {
            try {
                // await 은 async 안에서만 사용 가능
                    // 비동기를 동기처리할때 사용 
                await printUser2().then((r) => {
                    console.log(r);
                });
                throw new Error("오류처리");
            } catch(error) {
                console.log(error);
            }
            return "유저1";
        }

        //printUser = 프로미스 처럼 사용
        printUser().then(r => console.log(r));
        
    }

    return (
        <div>
            <button css={Btn} onClick={handleClick1}>Click 1</button>
            <button css={Btn} onClick={handleClick2}>Click 2 </button>
            <button css={Btn} onClick={handleClick3}>Click 3 </button>
        </div>
    );
}

export default Asynchronous;