class Counter extends React.Component {
    constructor(props) {
        super(props);
        this.handleAddOne = this.handleAddOne.bind(this);
        this.handleMinusOne = this.handleMinusOne.bind(this);
        this.handleReset = this.handleReset.bind(this);
        this.state = {
            count: 0,
        }
    }

    componentDidUpdate(prevProps, prevState) {
        if (prevState.count !== this.state.count) {
            const json = JSON.stringify(this.state.count);
            localStorage.setItem('count', json);
        }
    }

    componentDidMount() {
        try {
            const json = localStorage.getItem('count');
            const count = JSON.parse(json);
            if (count) {
                this.setState(() => ({ count }));
            }
        } catch (e) {
            //Fo nothing
        }
    }

    handleAddOne() {
        this.setState((prevState) => ({ count: prevState.count + 1 }));
        console.log("handleAllOne ");
    }
    handleMinusOne() {
        this.setState((prevState) => ({ count: prevState.count - 1 }));
    }
    handleReset() {
        this.setState(() => ({ count: 0 }));
    }
    render() {
        return (
            <div>
                <h1>Counter : {this.state.count}</h1>
                <button onClick={this.handleAddOne}>+1</button>
                <button onClick={this.handleMinusOne}>-1</button>
                <button onClick={this.handleReset}>reset</button>
            </div>
        );
    } 
}


ReactDOM.render(<Counter />, document.getElementById('app'));


// var template = (
//     <div>
//         <h1>{app.title}</h1>
//         <p>{app.subtitle}</p>
//         <p>{app.options.length > 0 ? "Here Your Options" : "no Options"}</p>
//         <ol>
//             <li>Item One</li>
//             <li>Item Two</li>
//             <li>Item Tree</li>
//         </ol>
//     </div>
// );

// var appRoot = document.getElementById("app");


// let count = 0;

// const addOne = () => {
//     count++;
//     renderTemplate();
// }

// const minusOne = () => {
//     count--;
//     renderTemplate();
// }

// const reset = () => {
//     count = 0;
//     renderTemplate();
// }


// const renderTemplate = () => {
//     var templateTwo = (
//         <div>
//             <h1>Count :{count}</h1>
//             <button onClick={addOne}>+1</button>
//             <button onClick={minusOne}>-1</button>
//             <button onClick={reset}>reset</button>
//         </div>
//     );
//     ReactDOM.render(templateTwo,appRoot);    
// }

// renderTemplate();

