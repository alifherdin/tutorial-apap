import React, { Component } from "react";
import CartItem from "../../components/CartItem";
import Button from '@mui/material/Button';
import './styles.modules.css';
import APIConfig from "../../api/APIConfig";
import { Link } from 'react-router-dom';


class Cart extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            cartItems: [],
            totalItems: 0,
            checkoutable: false,
        };
        this.handleCheckout = this.handleCheckout.bind(this);
        this.handleClickLoading = this.handleClickLoading.bind(this);
        this.doCheckout = this.doCheckout.bind(this);
    }

    handleClickLoading() {
        const currentLoading = this.state.isLoading;
        this.setState({ isLoading: !currentLoading });
        console.log(this.state.isLoading);
    }

    componentDidMount() {
        this.loadData();
    }

    async loadData() {
        try {
            const { data } = await APIConfig.get("/cart");
            let tempTotal = 0;

            for (let i = 0; i < data.result.length; i++) {
                tempTotal += data.result[i].quantity;
            }

            this.setState({ cartItems: data.result, totalItems: tempTotal });

            if (this.state.totalItems > 0) {
                this.setState({ checkoutable: true });
            } else {
                this.setState({ checkoutable: false });
            }

        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate()");
        return true;
    }

    async doCheckout() {
        try {
            const { data } = await APIConfig.get("/cart/checkout");
            this.setState({ checkoutable: false, totalItems: 0 });
            console.log("Test")
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    handleCheckout(event) {        
        if (this.state.checkoutable) {
            this.doCheckout();
            this.setState({ totalItems: 0 });
        }
    }


    render() {
        return (
            <div className='itemList'>
                <h1 className='title'>All Cart Items</h1>
                <Link to={'/'}>
                    <Button variant="outlined" style={{ position: 'fixed', top: 25, left: 25 }}> Back to Items List</Button>
                </Link> <br /><br />
                {this.state.checkoutable ? <Button variant="outlined" style={{ position: 'fixed', top: 25, right: 25 }} onClick={this.handleCheckout}> Checkout </Button>
                : null}
                <div>
                    {this.state.cartItems.map((ci) => (
                        <CartItem
                            key={ci.id}
                            id={ci.id}
                            title={ci.item.title}
                            price={ci.item.price}
                            description={ci.item.description}
                            category={ci.item.category}
                            quantity={ci.quantity}
                            totalHarga={ci.quantity * ci.item.price}
                        />
                    ))}
                </div>

            </div>
        )
    }
}

export default Cart;