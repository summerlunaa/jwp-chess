function deleteGame(gameId) {
    const password = prompt('비밀번호를 입력하세요.', '');

    fetch(`/game/` + gameId, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            id: gameId,
            password: password
        })
    }).then((response) => {
        if (response.status === 204) {
            alert("성공적으로 삭제하였습니다.");
        } else {
            throw response;
        }
        location.reload();
    }).catch(err => {
        err.text().then(msg => {
            alert(msg);
        })
    });
}
