console.log("memberRegister js in");

var compare_result = false;

//비밀번호중복여부확인
function fn_compare_pwd() {
    let pwd1 = document.getElementById('p');
    let pwd2 = document.getElementById('pCheck');
    let messageContainer = document.getElementById('password-match-message');

    if (pwd1.value === pwd2.value) {
        compare_result = true;
        messageContainer.innerHTML = "비밀번호가 일치합니다.";
        messageContainer.style.color = "green";
    } else {
        compare_result = false;
        messageContainer.innerHTML = "비밀번호가 일치하지 않습니다.";
        messageContainer.style.color = "red";
    }
}

//중복이 있다면 버튼이 비활성화되도록 설정
function checkFormValidity(){
    let joinBtn = document.getElementById('regBtn');
    let pwd = document.getElementById('p');
    let pwd2 = document.getElementById('pCheck');
    if(compare_result && pwd.value == pwd2.value){
        //결과가 true일때는 활성화 아닐시 비활성화 처리
        joinBtn.disabled = false; //활성화
    } else {
        joinBtn.disabled = true; //비활성화
    }
}

//이메일중복여부확인
document.getElementById('eCheck').addEventListener('click',()=>{
    let email = document.getElementById('e').value;
    let messageContainer = document.getElementById('email-match-message');
    emailCheckToServer(email).then(result => {
        console.log(result);
        if(result === '1'){
            messageContainer.innerHTML = "이미 존재하는 아이디입니다.";
            messageContainer.style.color = "red";
            compare_result = false;
        } else {
            messageContainer.innerHTML = "가입이 가능한 아이디입니다.";
            messageContainer.style.color = "green";
            compare_result = true;
        }
        checkFormValidity();
    })
})

//이메일 중복여부 확인
async function emailCheckToServer(email){
    try {
        const url = "/member/check/"+email;
        const resp = await fetch(url);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }

}



