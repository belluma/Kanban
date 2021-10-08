import React from 'react'
import { useAppDispatch } from '../../../../app/hooks';

//component imports
import {Button, Grid} from "@mui/material";

//interface imports

type Props = {
    // disableAdvance: boolean,
    // disableRevert: boolean,
    // checked: number[]
};

function ButtonContainer(props: Props){
    const dispatch = useAppDispatch();
    // const checkedLeft, checkedRight;


    return(
        <Grid item xs={1}>
        <Grid container direction="column" alignItems="center">
            <Button
                sx={{my: 0.5}}
                variant="outlined"
                size="small"
                // onClick={() => dispatch(test("abc"))}
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
        </Grid>
    )
}

export default ButtonContainer;
