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

            <h1>Courses</h1>
            <Link variant="contained" color="danger" to="/AddCourse">
                Add new Course
            </Link>
            <Paper elevation={3} style={paperStyle}>

                {courses.map(course=>(
                    <Paper elevation={6} style={{margin: "10px", padding: "15px", textAlign: "left"}} key={course.id}>
                        Id:{course.id}<br/>
                        Name:{course.name}<br/>
                        Content:{course.content}<br/>
                        Author:{course.author}<br/>
                        Tags:{course.tags}<br/>
                        <Link className="btn btn-outline-primary mx-2" to={`/editCourse/${Course.id}`}>
                            Edit
                        </Link>
                        <Button variant="contained" color="secondary" >
                            Delete
                        </Button>
                    </Paper>

                ))
                }


            </Paper>



        </Container>
    );
}