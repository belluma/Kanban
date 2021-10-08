import React from 'react'
import {Button, Grid} from "@mui/material";

//component imports

//interface imports

type Props = {};

function ButtonContainer(props: Props){
    return(
        <Grid container direction="column" alignItems="center">
            <Button
                sx={{my: 0.5}}
                variant="outlined"
                size="small"
                // onClick={() => advanceTodo('doing')}
                // disabled={left.length === 0}
                aria-label="move selected right"
            >
                &gt;
            </Button>
            <Button
                sx={{my: 0.5}}
                variant="outlined"
                size="small"
                // onClick={() => revertTodo('doing')}
                // disabled={middleChecked.length === 0}
                aria-label="move selected left"
            >
                &lt;
            </Button>
        </Grid>
    )
}

export default ButtonContainer;
