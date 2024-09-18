//THOMAS PETERSON

//CHART BUTTONS
const but_norm = document.getElementById("normal");
const but_narc = document.getElementById("narcoleptic");
const but_both = document.getElementById("both");
const img = document.getElementById("chart-img");

but_norm.onclick = () => {
    but_norm.classList.replace("isClicked");
    but_both.classList.replace("notClicked");
    but_narc.classList.replace("notClicked");
    img.src = img.src.replace('../../images/../../images/ProjectPart_03_Normal-Sleep.png');
};

but_narc.onclick = () => {
    but_narc.classList.replace("isClicked");
    but_norm.classList.replace("notClicked");
    but_both.classList.replace("notClicked");
    img.src = img.src.replace("../../images/../../images/ProjectPart_03_Narc-Sleep.png");
};

but_both.onclick = () => {
    but_both.classList.replace("isClicked");
    but_norm.classList.replace("notClicked");
    but_narc.classList.replace("notClicked");
    img.src = img.src.replace('../../images/../../images/ProjectPart_03_Both.png');
};

const fn = document.getElementById("First-name");
const ln = document.getElementById("Last-name");
const eml = document.getElementById("Email");
const pnumb = document.getElementById("Phone-number");

