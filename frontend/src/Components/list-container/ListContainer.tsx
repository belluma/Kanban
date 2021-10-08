import React from 'react'

//component imports
import {Container, Fab, Toolbar} from "@mui/material";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import ScrollTop from "./scroll-to-top/ScrollToTop";
import DesktopListContainer
    from "./desktop-list-container/DesktopListContainer";

//interface imports

type Props = {};

function ListContainer(props: Props){
    return(<div>
        <Toolbar id="back-to-top-anchor"/>
        <Container maxWidth={false} sx={{paddingTop:10}}>
            <DesktopListContainer />
        </Container>
    <ScrollTop {...props}>
        <Fab color="secondary" size="small" aria-label="scroll back to top">
            <KeyboardArrowUpIcon/>
        </Fab>
    </ScrollTop></div>
    )
}

export default ListContainer;

