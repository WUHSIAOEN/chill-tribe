
const username = document.querySelector('#supplier_name');
const password = document.querySelector('#password');
const cpassword = document.querySelector('#cPassword');
const phone = document.querySelector('#phone');
const email = document.querySelector('#email');
const id_number = document.querySelector('#id_number');

document.querySelector('button#build').addEventListener('click', () => {
	console.log("觸發");
	event.preventDefault();
	if (username.value.length < 5 || username.value.length > 50) {
		Swal.fire({
			icon: "error",
			title: "Oh...糟糕",
			text: "供應商名稱長度須介於5 ~ 50",
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

	if (password.value.length < 6 || password.value.length > 12) {
		Swal.fire({
			icon: "error",
			title: "Oh...糟糕",
			text: "密碼長度須介於6 ~ 12",
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
	
	if (id_number.value === "") {
			Swal.fire({
				icon: "error",
				title: "Oh...糟糕",
				text: "請重新輸入身分證",
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

	fetch(`${APP_CONFIG.BASE_URL}supplier/register`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify({
			supplier_name: username.value,
			password: password.value,
			cpassword: cpassword.value,
			phone: phone.value,
			email: email.value,
			id_number: id_number.value
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
					text: "恭喜您，註冊成功，請至Email收信！",
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
