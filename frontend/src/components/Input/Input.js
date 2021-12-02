import React, { useState } from 'react'
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';

const Input = ({ onSendMessage }) => {
    const [name, setName] = useState("")
    const [description, setDescription] = useState("")

    let onNameChange = (e) => {
        setName(e.target.value)
    }

    let onDescriptionChange = (e) => {
        setDescription(e.target.value)
    }

    let onSubmit = () => {
        setName("")
        setDescription("")
        onSendMessage(name, description);
    }

    return (
        <div className="input">
            <TextField id="outlined-basic" label="Name" variant="outlined"
                placeholder="Name"
                onChange={e => onNameChange(e)}
                value={name}
                style={{ height: "30px", width: "200px" }}
            />
            <TextField id="outlined-basic" label="Description" variant="outlined"
                placeholder="Description"
                onChange={e => onDescriptionChange(e)}
                value={description}
                style={{ height: "30px", width: "400px" }}
            />
            <Button variant="contained" color="primary" onClick={onSubmit}
                    style={{ height: "56px", width: "70px" }}>
                Send
            </Button>
        </div>
    );
}


export default Input