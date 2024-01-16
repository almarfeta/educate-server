import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container ,Paper,Button} from '@material-ui/core';
import { Link, useNavigate } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),

        },
    },
}));

export default function Course() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[name,setName]=useState('')
    const[content,setContent]=useState('')
    const[author,setAuthor]=useState('')
    const[tags,setTags]=useState('')
    const[courses,setCourses]=useState([])
    const classes = useStyles();
    let navigate = useNavigate();

    const handleClick=(e)=>{
        e.preventDefault()
        const course={name,content,author,tags}
        console.log(course)
        fetch("http://localhost:8080/api/v1/course/add",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(course)

        }).then(()=>{
            console.log("New Course added")
        })
        navigate("/")
    }

    useEffect(()=>{
        fetch("http://localhost:8080/api/v1/course/get/all")
            .then(res=>res.json())
            .then((result)=>{
                    setCourses(result);
                }
            )
    },[])
    return (

        <Container>
            <Paper elevation={3} style={paperStyle}>
                <h1 style={{color:"blue"}}><u>Add Course</u></h1>

                <form className={classes.root} noValidate autoComplete="off">

                    <TextField id="outlined-basic" label="Course Name" variant="outlined" fullWidth
                               value={name}
                               onChange={(e)=>setName(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="Course Content" variant="outlined" fullWidth
                               value={content}
                               onChange={(e)=>setContent(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="Course Author" variant="outlined" fullWidth
                               value={author}
                               onChange={(e)=>setAuthor(e.target.value)}
                    />
                    <TextField id="outlined-basic" label="Course Tags" variant="outlined" fullWidth
                               value={tags}
                               onChange={(e)=>setTags(e.target.value)}
                    />
                    <Button variant="contained" color="danger" onClick={handleClick}>
                        Submit
                    </Button>
                    <Link variant="contained" color="secondary" to="/">
                        Cancel
                    </Link>
                </form>

            </Paper>




        </Container>
    );
}