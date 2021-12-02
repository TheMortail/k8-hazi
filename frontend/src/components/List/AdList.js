import React, { useState, useEffect } from "react";
import Axios from "axios";
import Card from '@material-ui/core/Card';
import {Box, Container, Grid} from "@material-ui/core";

function AdList() {
    const [products, setProducts] = useState([]);

    // const URL = process.env.REACT_APP_BACKEND_URL || "localhost:8080";
    const URL = window._env_.REACT_APP_BACKEND_URL || process.env.REACT_APP_BACKEND_URL;

    const fetchProducts = async () => {
        const { data } = await Axios.get(
            "http://"+URL+"/api/ad/getAll"
        );
        const products = data;
        setProducts(products);
        console.log(products);
    };

    useEffect(() => {
        fetchProducts();
    }, []);

    return (
        <div>
            <Container maxWidth="sm">
                <div>
                    <Box sx={{ mt: 5 }}/>
                        <Grid container spacing={1}>
                            <Grid item xs={4}>
                                <h3>Name</h3>
                            </Grid>
                            <Grid item xs={8}>
                                <h3>Description</h3>
                            </Grid>
                        </Grid>
                </div>

                {products.map((product) => (
                    <div>
                        <Box sx={{ mt: 2 }}/>
                        <Card>
                            <Grid container spacing={1}>
                                <Grid item xs={4}>
                                    {product.name}

                                </Grid>
                                <Grid item xs={8}>
                                    {product.description}
                                </Grid>
                            </Grid>
                        </Card>
                    </div>
                ))}
            </Container>
        </div>
    );
}

export default AdList;