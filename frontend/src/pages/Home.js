import React, {useEffect, useState} from 'react'
import axios from "axios";
import { Link, useParams } from 'react-router-dom';

export default function Home(){
    const [courses, setCourses] = useState([]);
    const { id } = useParams();

    useEffect(() => {
        loadCourses();
    }, []);

    const loadCourses = async () => {
        const result = await axios.get("http://localhost:8080/api/v1/course/get/all");
        setCourses(result.data);
    };

    const deleteCourse = async (id) => {
        await axios.delete(`http://localhost:8080/api/v1/course/delete/id/${id}`);
        loadCourses();
    };

    return (
        <div className="container">
            <div className="flex flex-col items-center justify-center ">
                <div className="flex flex-row gap-2 py-2">
                </div>
                <img className="container-fluid img-fluid shadow rounded"
                     src="https://www.brightview.com/sites/default/files/styles/optimized/public/2023-04/website%20hero%20content%20article%201.png.jpg?itok=5XnvfpWU"
                     alt="Main banner"
                     width="100px"/>
            </div>


            <div className="py-4">
                <table className="table border shadow">
                    <thead>
                    <tr>
                        <th scope="col">S.N</th>
                        <th scope="col">Name</th>
                        <th scope="col">Author</th>
                        <th scope="col">Tags</th>
                        <th scope="col">Content</th>
                        <th scope="col">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    {courses.map((course, index) => (
                        <tr>
                            <th scope="row" key={index}>
                                {index + 1}
                            </th>
                            <td>{course.name}</td>
                            <td>{course.author}</td>
                            <td>{course.tags}</td>
                            <td>
                                <Link
                                    className="btn btn-primary mx-2"
                                    to={`data:application/pdf;base64,${course.content}`}
                                    download={course.name}
                                >
                                    Download
                                </Link>
                            </td>
                            <td>
                                <Link
                                    className="btn btn-warning mx-2"
                                    to={`/EditCourse/${course.id}`}
                                >
                                    Edit
                                </Link>
                                <button
                                    className="btn btn-danger mx-2"
                                    onClick={() => deleteCourse(course.id)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}