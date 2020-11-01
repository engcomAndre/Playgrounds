

export class Person{
    constructor(age){{
        this.age = age
    }}

    isAdult() {return this.age > 17};
   
    canDrink (){return this.age > 17};

    isSenior (){return this.age > 64};


    getAge(){
        return this.age;
    }
}

export const isAdult = (person) => {
    return person.getAge > 17 ;
}

export const canDrink = (person) => {
    return isAdult(isAdult(person)); 
 }

export default (person) => person.age > 64;