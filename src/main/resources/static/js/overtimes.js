// const overtimeId = document.getElementById('overtimeId').value
//
// const csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
// const csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;
//
// const overtimeContainer = document.getElementById('overtimeContainer')
//
// const overtimeForm = document.getElementById('overtimeForm')
// overtimeForm.addEventListener("submit", handleOvertimeSubmit)
//
// const allOvertimes = []
//
// const displayOvertimes = (overtimes) => {
//     overtimeContainer.innerHTML = overtimes.map(
//         (o) => {
//             return asOvertime(o)
//         }
//     ).join('')
// }
//
// async function handleOvertimeSubmit(event) {
//     event.preventDefault();
//
//     const form = event.currentTarget;
//     const url = form.action;
//     const formData = new FormData(form);
//
//     try {
//         const responseData = await postFormDataAsJson({url, formData});
//
//         overtimeContainer.insertAdjacentHTML("afterbegin", asOvertime(responseData));
//         form.reset();
//
//     } catch (error) {
//
//         let errorObj = JSON.parse(error.message);
//
//         if (errorObj.fieldWithErrors) {
//             errorObj.fieldWithErrors.forEach(
//                 e => {
//                     let elementWithError = document.getElementById(e);
//                     if (elementWithError) {
//                         elementWithError.classList.add("is-invalid");
//                     }
//                 }
//             )
//         }
//     }
// }
//
// async function postFormDataAsJson({url, formData}) {
//
//     const plainFormData = Object.fromEntries(formData.entries());
//     const formDataAsJSONString = JSON.stringify(plainFormData);
//
//     const fetchOptions = {
//         method: "POST",
//         headers: {
//             [csrfHeaderName]: csrfHeaderValue,
//             "Content-Type": "application/json",
//             "Accept": "application/json"
//         },
//         body: formDataAsJSONString
//     }
//
//     const response = await fetch(url, fetchOptions);
//
//     if (!response.ok) {
//         const errorMessage = await response.text();
//         throw new Error(errorMessage);
//     }
//
//     return response.json();
// }
//
// function asOvertime(o) {
//     let overtimeHtml = `<div id="overtimeCntr-${o.overtimeId}">`
//     overtimeHtml += `<h4>${o.user} (${o.created})</h4><br/>`
//     overtimeHtml += `<textarea rows="3" class="form-control m-md-auto" readonly>${o.name}</textarea>`
//     overtimeHtml += `</div>`
//
//     return overtimeHtml
// }
//
// fetch(`/api/users/profile`).then(response => response.json()).then(data => {
//     for (let overtime of data) {
//         allOvertimes.push(overtime)
//     }
//     displayOvertimes(allOvertimes)
// })
