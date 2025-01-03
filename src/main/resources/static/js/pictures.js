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
    let pictureHtml = `
<section class="view-picture-section" id="pictureContainer-${p.picture}">
    <section class="img-section">
        <h2>${p.author}<br>(${p.created})</h2>
        <p>${p.title}</p>
        <section class="section-a">
            <a href="${p.url}"><img src="${p.url}" class="img-cloudinary" alt="Responsive image"></a>
        </section>
    </section>
</section>`
    return pictureHtml
}

fetch(`/reports/${reportId}/report-details/pictures/all`)
    .then(response => response.json()).then(data => {

    for (let picture of data) {
        allPictures.push(picture)
    }
    displayPictures(allPictures)
})