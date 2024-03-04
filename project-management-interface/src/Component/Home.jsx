import React from 'react'
import "./styles/stylesheet.css"
import Sidebar from './Sidebar'
import Navbar from './Navbar'

function Home() {
  return (
    <>
       <div className="page-row">
           <Sidebar/>
           <div className="project-wrapper">
            <Navbar/> 
           </div>
       </div>
       
    </>
  )
}

export default Home