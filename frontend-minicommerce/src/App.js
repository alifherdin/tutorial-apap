import React, { Component } from "react";
import Layout from "./components/layout";
import ItemList from "./containers/itemlist";


class App extends Component {
    render() {
        return (
            <Layout>
                <ItemList></ItemList>
            </Layout>
        );
    }
}

export default App;
