//Assignments Toggle Arrow
const h2_1 = document.getElementById("h2-1");
const h2_2 = document.getElementById("h2-2");
let assignments = document.getElementById("assignments");

h2_1.addEventListener('click', () => {
    assignments.classList.toggle("columns");
    h2_1.classList.toggle("hidden");
    h2_2.classList.toggle("hidden");
});
h2_2.addEventListener('click', () => {
    assignments.classList.toggle("columns");
    h2_1.classList.toggle("hidden");
    h2_2.classList.toggle("hidden");
});

//Projects Toggle Arrow
const h2_1_p = document.getElementById("projects-h2");
const h2_2_p = document.getElementById("projects-h2-2");
let projects = document.getElementById("projects");

h2_1_p.addEventListener('click', () => {
    projects.classList.toggle("columns");
    h2_1_p.classList.toggle("hidden");
    h2_2_p.classList.toggle("hidden");
});
h2_2_p.addEventListener('click', () => {
    projects.classList.toggle("columns");
    h2_1_p.classList.toggle("hidden");
    h2_2_p.classList.toggle("hidden");
});