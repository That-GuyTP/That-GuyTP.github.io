//Same method as shows in other json examples
const getIngredients = async() => {
    const url = "https://www.thecocktaildb.com/api/json/v1/1/list.php?i=list"
    try {
        const response = await fetch(url);
        return response.json();
    } catch(error) {
        console.log("---Error retreiving URL data.---");
        console.log(error);
    }
};

//Same method as shown in other json examples.
const showIngres = async() => {
    const ingres = await getIngredients();
    console.log(ingres);
    ingres.drinks.forEach((ingre) => {
        document.getElementById("cocktails").append(getIngreDiv(ingre));
    });
};


const getIngreDiv = (ingre) => {
    const div = document.createElement("div");
    div.classList.add("one");
    //div.classList.add("hidden");

    // Retrieve Ingre
    const p = document.createElement("p");
    p.innerHTML = ingre.strIngredient1;
    div.append(p);
    
    //Add Onclick Feature
    div.addEventListener('click', () => {
        div.classList.toggle("ingre");
        console.log("clicked");
    });

    return div;
};

showIngres();

/*The concept of this is:
    1. Create a main array to store the selected filters.
    2. For each filter fetch the info from the api url.
    3. Add the list of drinks for each filter in the array.
    4. When printing out the filter's info, check if there are matching infos for each array and only print those.
    5. Success.
* TLDR: Filters the drinks based on the selected ingredients.
*/
const getIngreFilter = async() => {
    const ingreList = document.getElementById("cocktails");
    const ingreListSelected = ingreList.getElementsByClassName("ingre");

    if (ingreListSelected == null || ingreListSelected.length == 0) {
        return;
    }

    let commonDrinks = null;
    for (let i = 0; i < ingreListSelected.length; i++) {
        const ingredient = ingreListSelected[i].firstElementChild.innerHTML;
        const url = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=" + `${ingreListSelected[0].firstElementChild.innerHTML}`;
        try {
            const response = await fetch(url);
            const result = await response.json();

            if (commonDrinks == null) {
                commonDrinks = result.drinks;
            } else {
                commonDrinks = commonDrinks.filter(drink => 
                    result.drinks.some(newDrink => newDrink.idDrink === drink.idDrink)
                );
            }
        } catch (error) {
            console.log("---Error retrieving URL data.---");
            console.log(error);
        }
    }
    return commonDrinks;
};

//Basically dispalys the filtered drinks with their details. It's like the standard "add to div" function we'd use to import an details of an api.
const showIngreFilter = async() => {
    const filters = await getIngreFilter();
    const filterDiv = document.getElementById("filter");

    // Clear previous results
    filterDiv.innerHTML = '';

    if (!filters || filters.length == 0) {
        filterDiv.innerHTML = '<p>No drinks found for selected ingredients.</p>';
        return;
    }

    for (const filter of filters) {
        const drinkDetailUrl = `https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=${filter.idDrink}`;
        try {
            const response = await fetch(drinkDetailUrl);
            const drinkDetails = await response.json();

            if (drinkDetails.drinks && drinkDetails.drinks.length > 0) {
                filterDiv.append(getDrinkDiv(drinkDetails.drinks[0]));
            }
        } catch (error) {
            console.log("---Error retrieving drink details.---");
            console.log(error);
        }
    }
};

//Drink Div Creation
//Creates the div and it's content for the list of drinks that can be made with the ingredients provided.
const getDrinkDiv = (drink) => {
    const div = document.createElement("div");
    div.classList.add("drink");

    const name = document.createElement("h3");
    name.innerHTML = drink.strDrink;

    const img = document.createElement("img");
    img.src = drink.strDrinkThumb;
    img.alt = drink.strDrink;

    const instructions = document.createElement("p");
    instructions.innerHTML = drink.strInstructions;

    div.append(name, img, instructions);
    return div;
};

const getIngreFilterCT = (filter) => {
    const div = document.createElement("div");
    
    const p = document.createElement("p");
    p.innerHTML = filter.strDrink;
    div.append(p);

    return div;
};

//Button Functionality
//Shows all the available drinks you can make with the selected ingredients when clicked.
const btn = document.getElementById("btn-1");
btn.onclick = () => {
    showIngreFilter();
};
