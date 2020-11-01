class VisibilityTogle extends React.Component {
    constructor(props) {
        super(props)
        this.handleTogleVisibility = this.handleTogleVisibility.bind(this);

        this.state = {
            visibility: false
        }
    }

    handleTogleVisibility() {
        this.setState((prevState) => {
            return {
                visibility: !prevState.visibility
            }
        });
    }

    render() {
        return (
            <div>
                <h1>Visibility Togle</h1>
                <button onClick={this.handleTogleVisibility}>{this.state.visibility ? "Hide Details" : "Show Details"}</button>
                {this.state.visibility && (
                    <div>
                        <p>Any content</p>
                    </div>
                )}
            </div>
        );
    }
}


ReactDOM.render(<VisibilityTogle />, document.getElementById('app'));























// let visibility = true;

// const togleVisibility = () => {
//     visibility = !visibility;
//     render();
// }

// const render = () => {
//     const jsx = (
//         <div>
//             <h1>Visibility Togle</h1>
//             <button onClick={togleVisibility}>{visibility ? "Hide Details" : "Show Details"}</button>
//             {visibility && (
//                 <div>
//                     <p>Any content</p>
//                 </div>
//             )}
//         </div>
//     );
//     ReactDOM.render(jsx, document.getElementById("app"));
// }

// render();