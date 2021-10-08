import React from 'react'
import {AppBar, Toolbar, Typography} from "@mui/material";

//component imports

//interface imports

type Props = {};

function Header(props: Props){
    return(
        <AppBar >
            <Toolbar>
                <Typography variant="h6" component="div">
                    Scroll to see button
                </Typography>
            </Toolbar>
        </AppBar>
    )
}

export default Header;
