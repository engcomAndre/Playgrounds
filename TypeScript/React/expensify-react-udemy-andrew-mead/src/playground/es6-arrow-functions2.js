//arguments objects allowed in function default declaration scoped
const add = function (a, b) {//passed parameters are acessible for for a keyword 'arguments'

    console.log(arguments);
    return a + b;
}

//arguments objects is not allowed in arrow function declaration scoped
const addArrow = (a, b) => {//passed parameters are acessible for for a keyword 'arguments'
    // console.log(arguments);//undefined
    return a + b;
}
// console.log(add(1, 2));
// console.log(addArrow(1, 2));


//this in object scoped

const user = {
    name: "Any Name",
    cities: ["First City", "Second City", "Third City"],
    printCities: function () {
        const that = this;
        this.cities.forEach(function (city) {
            console.log(that.name + " has lived in " + city);

        });
    }
}

const userArrow = {
    name: "Any Name",
    cities: ["First City", "Second City", "Third City"],
    printCities: function () {
        this.cities.forEach((city) => {
            console.log(this.name + " has lived in " + city);

        });
    },
    printCities2() {
        this.cities.forEach((city) => {
            console.log(this.name + " has lived in " + city);

        });
    },
    printCitiesMap() {
        return this.cities.map((city) => (this.name + "(MAP) has lived in " + city));
         
    }
}

// console.log(userArrow.printCitiesMap());


const multiplier = {
    numbers : [1,2,3,4,5,6,7,8,9],
    multiplyBy : 3,
    multiply(){
        return this.numbers.map((number) => (number * this.multiplyBy));
    }
};


console.log(multiplier.multiply());
