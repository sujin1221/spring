console.log("comment js in~");

document.getElementById('cmtPostBtn').addEventListener('click',()=>{
    const cmtText = document.getElementById('cmtText');
    if(cmtText.value == null || cmtText.value == ''){
       alert("댓글을 입력해주세요.");
       cmtText.focus();
       return false; //큰 의미 없음 여기서 끝내려고 하는거임
    } else {
        let cmtData = {
            bno: bnoVal,
            writer: document.getElementById('cmtWriter').innerText,
            content: cmtText.value
        };
        console.log(cmtData);
        postCommentToServer(cmtData).then(result=>{
            if(result === '1'){
                alert("댓글 등록 성공!");
                cmtText.value = "";
            }
            //화면에 뿌리기
            spreadCommentList(cmtData.bno);

        })

    }
})

async function postCommentToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method: "post",
            headers: {
                'content-type':'application/json; charset=utf-8'
            },
            body: JSON.stringify(cmtData)
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function getCommentListFromServer(bno, page){
    try {
        const resp = await fetch("/comment/"+bno+"/"+page);
        const result = await resp.json(); //댓글리스트를 리턴해야하므로 제이슨이 와야함!
        return result;
    } catch (error) {
        console.log(error);
    }
};

function spreadCommentList(bno, page=1){
    getCommentListFromServer(bno, page).then(result=>{
        console.log(result.cmtList);
        //댓글 모양대로 뿌리기
        if(result.length > 0){
            const ul = document.getElementById('cmtListArea');
            ul.innerHTML = '';
            for(let cvo of result.cmtList){
                let li = `<li class="list-group-item" data-cno="#{cvo.cno}">`;
                li += `<li class="list-group-item">`;
                li += `<div class="mb-3">`;
                li += `<div class="fw-bold">Writer</div>`;
                li += `content </div>`;
                li += `<span class="badge text-bg-primary">modAt</span>`;
                li += `</li> </ul>`;
                ul.innerHTML += li;
            }
        } else {
            let li = `<li class="list-group-item">Comment List Empty</li>`;
        }
    });
}
    