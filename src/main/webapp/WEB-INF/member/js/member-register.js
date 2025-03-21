
const username = document.querySelector('#member_name');
const password = document.querySelector('#password');
const cpassword = document.querySelector('#cPassword');
const phone = document.querySelector('#phone');
const email = document.querySelector('#email');

document.querySelector('button#build').addEventListener('click', () => {
	console.log("觸發");
	event.preventDefault();
	if (username.value.length < 2 || username.value.length > 50) {
		//			alert('使用者名稱長度須介於2 ~ 50');
		Swal.fire({
			icon: "error",
			title: "Oh...糟糕",
			text: "使用者名稱長度須介於2 ~ 50",
		});
		return;
	}

	if (password.value.length < 4 || password.value.length > 12) {
//		alert('密碼長度須介於6 ~ 12');
		Swal.fire({
					icon: "error",
					title: "Oh...糟糕",
					text: "密碼長度須介於4 ~ 12",
				});
		return;
	}

	if (password.value !== cpassword.value) {
//		alert('密碼與確認密碼不同');
		Swal.fire({
							icon: "error",
							title: "Oh...糟糕",
							text: "密碼與確認密碼不同",
						});
		return;
	}

	fetch(`http://localhost:8080/chill-tribe/member/register`, {
//	fetch('register', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			membername: username.value,
			password: password.value,
			cpassword: cpassword.value,
			phone: phone.value,
			email: email.value,
		})
	})
		// console.log(resp)
		.then(resp => {
			return resp.json();
		})
		.then(body => {
//			debugger;
			const { successful, errMsg } = body;
			if (successful) {
			        Swal.fire({
			            icon: "success",
			            title: "註冊成功",
			            text: "恭喜您，註冊成功囉！",
			        });
			    } else {
			        Swal.fire({
			            icon: "error",
			            title: "Oh...糟糕",
			            text: errMsg || "發生錯誤，請稍後再試。",
			        });
			    }
		});
});
