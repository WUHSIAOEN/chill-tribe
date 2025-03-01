
	const username = document.querySelector('#supplier_name');
	const password = document.querySelector('#password');
	const cPassword = document.querySelector('#cPassword');
	const phone = document.querySelector('#phone');
	const email = document.querySelector('#email');
	const id_number = document.querySelector('#id_number');
	
	document.querySelector('button#build').addEventListener('click', () => {
		console.log("觸發");
		event.preventDefault();
		if (username.value.length < 5 || username.value.length > 50) {
			alert('供應商名稱長度須介於5 ~ 50');
			return;
		}
		
		if (password.value.length < 6 || password.value.length > 12 ) {
			alert('密碼長度須介於6 ~ 12');
			return;
		}
		
		if (password.value !== cPassword.value) {
			alert('密碼與確認密碼不同');
			return;
		}
		
		fetch('../supplier/register', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				supplier_name: username.value,
				password: password.value,
				cPassword: cPassword.value,
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
		 	 alert(successful ? '註冊成功' : errMsg);
		 });
	});
