import React  from 'react'

//component imports
import {Card, CardContent, CardHeader, Popper, Typography} from "@mui/material";

//interface imports
import {ITodo} from '../../../../../interfaces/ITodo';

type Props = {
    todo: ITodo,
    open: boolean
    anchorRef:HTMLElement | null
}

function TodoDetails({todo, open, anchorRef}: Props) {
    const {id, title, description, status, created, deadline} = todo;

    return (
        <Popper open={open} anchorEl={anchorRef}>
            <Card>
                <CardHeader title={title} subheader={`deadline: ${deadline}`}/>
                <CardContent>
                    <Typography variant="body2" color={"text.secondary"}>
                        {description}
                    </Typography>
                </CardContent>

            </Card>
        </Popper>
    )
}

export default TodoDetails;
