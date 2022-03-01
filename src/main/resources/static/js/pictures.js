const picturesContainer = document.getElementById('pictureContainer')
const allPictures = []

const displayPictures = (pictures) => {
    picturesContainer.innerHTML = pictures.map(
        (p) => {
            return viewPicture(p)
        }
    ).join('')
}

async function handlePicture(event) {
    event.preventDefault();

    const form = event.currentTarget;
    const url = form.action;
    const formData = new FormData(form);

    try {
        const responseData = await postFormDataAsJson({url, formData});

        picturesContainer.insertAdjacentHTML("afterbegin", viewPicture(responseData));
        form.reset();

    } catch (error) {

        let errorObj = JSON.parse(error.message);

        if (errorObj.fieldWithErrors) {
            errorObj.fieldWithErrors.forEach(
                e => {
                    let elementWithError = document.getElementById(e);
                    if (elementWithError) {
                        elementWithError.classList.add("is-invalid");
                    }
                }
            )
        }
    }
}


function viewPicture(p) {
    let pictureHtml =
        `<br><div class="card" id="pictureContainer-${p.picture}"><div class="card-body">`
    pictureHtml +=
        `<br><h4>${p.author} (${p.created})</h4><br/>`
    pictureHtml +=
        `<p>${p.title}</p>`
    pictureHtml +=
        `<a href="${p.url}"><img src="${p.url}" class="img-fluid" alt="Responsive image"></a>`
    pictureHtml +=
        `<br><div>
<form  id="test-form">
      <input type="hidden" name="public_id" value="${p.publicId}"/>
      <br>
    
      <button class="btn btn-outline-danger" type="submit">DELETE</button>
 </form>
</div></div></div><br>`

    return pictureHtml
}
const form = document.getElementById('test-form');
form.addEventListener('submit', async event => {
    event.preventDefault();     // This prevents the form from submitting using POST

    const idInput = form.querySelector('input[name="public_id"]');
    const public_id = idInput.value;

    const res = await fetch(`/reports/${reportId}/report-details/pictures/delete/${public_id}`, {
        method: 'DELETE',
    });
    const json = await res.json();

    console.log(json);
});

fetch(`/reports/${reportId}/report-details/pictures/all`)
    .then(response => response.json()).then(data => {
    for (let picture of data) {
        allPictures.push(picture)
    }
    displayPictures(allPictures)
})