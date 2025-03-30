const username = document.querySelector('#member_name');
const password = document.querySelector('#password');
const cpassword = document.querySelector('#cPassword');
const phone = document.querySelector('#phone');
const email = document.querySelector('#email');
const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

document.querySelector('button#build').addEventListener('click', () => {
	console.log("觸發");
	event.preventDefault();
	if (username.value.length < 2 || username.value.length > 50) {
		Swal.fire({
			icon: "error",
			title: "Oh...糟糕",
			text: "使用者名稱長度須介於2 ~ 50",
		});
		return;
	}
	
	if (email.value.indexOf('@') === -1) {
			Swal.fire({
				icon: "error",
				title: "Oh...糟糕",
				text: "Email格式不正確",
			});
			return;
		}

	if (password.value.length < 4 || password.value.length > 12) {
		Swal.fire({
					icon: "error",
					title: "Oh...糟糕",
					text: "密碼長度須介於4 ~ 12",
				});
		return;
	}

	if (password.value !== cpassword.value) {
		Swal.fire({
							icon: "error",
							title: "Oh...糟糕",
							text: "密碼與確認密碼不同",
						});
		return;
	}
	
	if (phone.value === "") {
			Swal.fire({
								icon: "error",
								title: "Oh...糟糕",
								text: "請重新輸入手機號碼",
							});
			return;
		}

	fetch(`${APP_CONFIG.BASE_URL}member/register`, {
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
			const { successful, errMsg } = body;
			if (successful) {
			        Swal.fire({
			            icon: "success",
			            title: "註冊成功",
			            text: "恭喜您，註冊成功囉！",
						footer: '<a href="http://localhost:8080/chill-tribe/chilltribe.html">前往首頁</a>'
						}).then((result) => {
						  if (result.isConfirmed) {
						    const loginModal = new bootstrap.Modal(document.getElementById('login'));
						    loginModal.show();
						  }
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
