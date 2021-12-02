import React, { Component } from "react";


class ItemList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [
                {
                    id: 1,
                    title: "Nintendo Switch",
                    price: "Rp5.000.000",
                    description: "The video game system you can play at home or on the go.",
                    category: "Console",
                },
                {
                    id: 2,
                    title: "Playstation 5",
                    price: "Rp12.000.000",
                    description: "A home video game console developed by Sony Interactive Entertainment.",
                    category: "Console",
                },
                {
                    id: 3,
                    title: "ASUS ROG Zephyrus G14",
                    price: "Rp17.000.000",
                    description: "ASUS ROG Zephyrus G14 is a successful equipment for work and entertainment.",
                    category: "Laptop",
                }
            ],
        };
    }
    componentDidMount() {
        console.log("componentDidMount()");
    }
    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate()");
    }
    render() {
        console.log("render()");
        return (
            <div>
                <h1>All Item</h1>
                <p>Item 1, 2, 3, dst</p>
            </div>
        );
    }
}
export default ItemList;