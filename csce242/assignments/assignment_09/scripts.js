//Modal Constants
const modal = document.getElementById("modal");
const modalImg = document.getElementById("modal-img");
const modalText = document.getElementById("modal-text");
const closeModal = document.querySelector(".close");

//Birds
class Bird {
    constructor(name, size, lifespan, food, habitat, interesting_fact) {
        this.name = name;
        this.size = size;
        this.lifespan = lifespan;
        this.food = food;
        this.habitat = habitat;
        this.interesting_fact = interesting_fact;
    }

    get item() {
        const div = document.createElement("div");
        div.classList.add("bird");

        //TITLE
        const h2 = document.createElement("h2");
        h2.innerHTML = this.name;
        h2.classList.add("bird-h2");
        div.append(h2);

        //IMAGE
        const img = document.createElement("img");
        img.setAttribute("src", "./images/" + this.name + ".jpg");
        img.classList.add("bird-img");
        div.append(img);

        //MODAL
        div.addEventListener('click', () => {
            console.log(this.name);
            modalImg.src = "./images/" + this.name + ".jpg";
            modalText.innerHTML = "<strong>Size:</strong> " + this.size 
                                + "<strong><br>Lifespan:</strong> " + this.lifespan 
                                + "<strong><br>Food:</strong> " + this.food 
                                + "<strong><br>Habitat:</strong> " + this.habitat 
                                + "<strong><br>Interesting Fact:</strong> " + this.interesting_fact;
            modal.style.display = "block";
        });
        
        return div;
    }
};

//Close Modal
closeModal.addEventListener('click', () => {
    modal.style.display = "none";
});

window.addEventListener('click', (event) => {
    if(event.target == modal) {
        modal.style.display = "none";
    }
});

//Create Birds
const birds = [];
birds.push(new Bird("Hummingbird", "2.5 inches", "3-5 Years", "Nectar or sugar water", "trees", "They're wings beat fast."));
birds.push(new Bird("Blue Jay", "3.4 inches", "3-5 Years", "Other birds", "Trees", "They're mean."));
birds.push(new Bird("Cardinal", "10 inches", "1000 Years", "Seeds", "Houses", "They're red."));
birds.push(new Bird("Robin", "2 KM", "5 seconds", "Bananas", "Bat Caves", "They rob(in) banks."));


birds.forEach((bird) => {
    document.getElementById("bird-list").append(bird.item);
});