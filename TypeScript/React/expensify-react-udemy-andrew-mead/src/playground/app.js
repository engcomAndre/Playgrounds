
class IndecisionApp extends React.Component {
    constructor(props) {
        super(props);
        this.handleAddOptions = this.handleAddOptions.bind(this);
        this.handlePick = this.handlePick.bind(this);
        this.handleDeleteOptions = this.handleDeleteOptions.bind(this);
        this.handleDeleteOption = this.handleDeleteOption.bind(this);
        this.state = {
            options: []
        }
    }

    componentDidUpdate(prevProps, prevState) {
        if (prevState.options.length != this.state.options.length) {
            const json = JSON.stringify(this.state.options);
            localStorage.setItem('options', json);
        }
    }

    componentDidMount() {
        try {
            const json = localStorage.getItem('options');
            const options = JSON.parse(json);
            if (options) {
                this.setState(() => ({ options }));
            }
        } catch (e) {
            //Fo nothing
        }
    }

    

    handleDeleteOptions() {
        this.setState((prevState) => ({ options: [] }));
    }

    handleDeleteOption(optionToRemove) {
        console.log(optionToRemove);
        this.setState((prevState) => ({
            options: prevState.options.filter((option) => { return optionToRemove !== option })
        }));
    }

    handlePick() {
        const rand = Math.floor(Math.random() * this.state.options.length);
        const opt = this.state.options[rand];
        alert(opt);
    }

    handleAddOptions(option) {
        if (!option) {
            return "Enter the valid value to add item";
        } else if (this.state.options.indexOf(option) > -1) {
            return "This option already exists";
        }
        this.setState((prevState) => ({ options: prevState.options.concat([option]) }));
    }
    render() {
        return (
            <div>
                <Header subtitle={this.state.subtitle} />
                <Action
                    hasOptions={this.state.options.length > 0}
                    handlePick={this.handlePick}
                />
                <Options
                    options={this.state.options}
                    handleDeleteOptions={this.handleDeleteOptions}
                    handleDeleteOption={this.handleDeleteOption}
                />
                <AddOption
                    handleAddOptions={this.handleAddOptions}
                />
            </div>
        );
    }
}

const Header = (props) => {
    return (
        <div>
            <h1>{props.title}</h1>
            <h2>{props.subtitle}</h2>
        </div>
    );
}

Header.defaultProps = {
    title: "Indecision",
    subtitle: "Put Your life in handes of computer..."
}

const Action = (props) => {
    return (
        <div>
            <button
                onClick={props.handlePick}
                disabled={!props.hasOptions}
            >What should I do?</button>
        </div>
    );

}

const Options = (props) => {
    return (
        <div>
            <button onClick={props.handleDeleteOptions}>Remove All</button>
            {props.options.length === 0 &&  <p>Please add options to get started!</p>}
            {props.options.map((opt) =>
                <Option
                    key={opt}
                    option={opt}
                    handleDeleteOption={props.handleDeleteOption}
                />)}
        </div>
    );
};


const Option = (props) => {
    return (
        <div>
            {props.option}
            <button onClick={(event) => props.handleDeleteOption(props.option)}>Remove</button>
        </div>
    );
}

class AddOption extends React.Component {
    constructor(props) {
        super(props)
        this.handleAddOptions = this.handleAddOptions.bind(this);
        this.state = {
            error: undefined
        }
    }
    handleAddOptions(event) {
        event.preventDefault();
        const option = event.target.elements.option.value.trim();
        const error = this.props.handleAddOptions(option);

        this.setState((prevState) => ({ error: error }));

        if(!error){
            event.target.elements.option.value = "";
        }
    }
    render() {
        return (
            <div>
                {this.state.error && <p>{this.state.error}</p>}
                <form onSubmit={this.handleAddOptions}>
                    <input type="text" name="option" />
                    <button> Add Options</button>
                </form>
            </div>
        );
    }
}



ReactDOM.render(<IndecisionApp />, document.getElementById('app'));



// import subtract, { square, add } from './utils.js'
// import senior, { Person, isAdult, canDrink } from './playground/person'

// console.log("app.js is runnig");

// console.log(square(5));

// console.log(add(5));

// console.log(subtract(5, 4));

// const personA = new Person(15);
// console.log("Adult : " + isAdult(personA));
// console.log(`Drink : ${canDrink(personA)}`);
// console.log("Senior :" + senior(personA));


// const personB = new Person(18);
// console.log(`Adult : ${isAdult(personB)}`);
// console.log(`Drink : ${canDrink(personB)}`);
// console.log(`Senior : ${senior(personB)}`);

// const personC = new Person(65);
// console.log(`Adult : ${isAdult(personC)}`);
// console.log(`Drink : ${canDrink(personC)}`);
// console.log(`Senior : ${senior(personC)}`);
// import validator from 'validator'

// console.log(validator.isEmail("com.br"));

// console.log(validator.isEmail("asd@com.br"));

