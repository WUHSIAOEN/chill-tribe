$(function () {


    // let urlParams = new URLSearchParams(window.location.search);
    // console.log(urlParams.toString());




    fetch("/chill-tribe/orders/order", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            activityId: 34,
            memberId: 1,
            quantity: 2,
            orderStatus: "no_payment_required",
            paymentMethod: "none",
            orderContact: "小名",
            contactMail: "aaabbbcc@gmail.com",
            contactPhone: "456196582",
            requirement: "無"
        }),
    })
    .then(resp => {
        if (resp.ok) {
            return resp.json();
        } else {
            const { status, statusText } = resp;
            throw Error(`${status}: ${statusText}`);
        }
    })
    .then(order => {
        console.log(order);
    })

})
