import React from 'react'

function ProjectSection() {
  return (
    <>
        <div className="project-section">
            <div className="container">
                <h6 className='page-section-header'>School project</h6>
                <div className="filter_project_section">
                    <div className="page-row project_filter_row">
                        <div className="table-filters">
                            <div className="page-row">
                                <form action="" className='table_filter_col'>
                                    <i className="lni lni-search"></i>
                                    <input placeholder='Search' type="text"/>
                                </form>
                                <div className="peron_filter table_filter_col">
                                    <i className="lni lni-users"></i>
                                    <p>Filter by person</p>
                                </div>
                                <div className="filter_sort table_filter_col">
                                    <i class="lni lni-sort-alpha-asc"></i>
                                    <p>Sort</p>
                                </div>
                            </div>
                        </div>
                        <div className="project_create_table_invite">
                            <div className="project_invite">
                               <div className="project_members">
                                   <span>M</span>
                                   <span className='member_letter'>d</span>
                                   <span className='member_letter'>T</span>
                               </div> 
                                <div className="invite_btn">
                                    <p>invite  </p>
                                    <i class="lni lni-circle-plus"></i>
                               </div>   
                               <button>
                                  <i class="lni lni-layers"></i>
                                  <span>create table</span>
                               </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="table_section">
                    <h6 id='table_title'>Table name</h6>
                    <div className="project_table">
                        <div className="page-row">
                            <span className='field_name col_name'>Title</span>
                            <span className='field_name col_name'>Description</span>
                            <span className='field_name col_name'>Owner</span>
                            <span className='field_name col_name'>start-date</span>
                            <span className='field_name col_name'>end-date</span>
                            <span className='field_name col_name'>status</span>
                           <div className='more  more-col'> <i className="lni lni-more "></i></div>
                        </div>
                        <div className="page-row table_task_row">
                            <div className="edit_task_box">
                                <div className="box-arrow"></div>
                                <p>
                                    <i className="lni lni-trash-can"></i>
                                    <span>Delete</span>
                                </p>
                                 <p>
                                    <i className="lni lni-clipboard"></i>
                                    <span>Duplicate</span>
                                </p>
                                 <p>
                                   <i className="lni lni-circle-plus"></i>
                                    <span>Delete</span>
                                </p>
                            </div>
                            <span className='field_name table-task task-title'> <i className="more-task lni lni-more"></i>Item title</span>
                            <span className='field_name table-task'>Item Description</span>
                            <span className='field_name table-task'>John Doe</span>
                            <input type="date" className='field_name table-task' />
                            <input type="date" className='field_name table-task' />
                            <span className='field_name table-task'>status</span>
                            <div className='more'></div>
                        </div>
                          <div className="page-row table_task_row">
                            <span className='field_name table-task task-title'> <i className="more-task lni lni-more"></i>Item title</span>
                            <span className='field_name table-task'>Item Description</span>
                            <span className='field_name table-task'>John Doe</span>
                            <input type="date" className='field_name table-task' />
                            <input type="date" className='field_name table-task' />
                            <span className='field_name table-task'>status</span>
                            <div className='more'></div>
                        </div>
                       
                    </div>
                </div>
            </div>
        </div>
    </>
  )
}

export default ProjectSection