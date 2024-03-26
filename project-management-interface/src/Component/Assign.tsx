import React from 'react'

function Assign(props:any) {
  return (
    <>
      <div className="assign_section" style={{height:props.assignModel +"%"}}>
        <div className="assign_container">
          <i className="lni lni-close"></i>
           <div className="task_members">
            <h6>Task members</h6>
            {
              props.taskMembers ?
                props.taskMembers.map(member =>
              <div className="task_member_name">
                <span className='assignee_letters'>M</span>
                <div className='assignee_details'>
                  <span>{member.username}</span>
                  <span>email@email.com</span>
                </div>
              </div>
            ):""}
          </div>
          <div className="project_members_list">
            <h6>Project members list: </h6>
            {props.projectPeople.map(member =>
               <div className="project_members_names">
                  <div className="task_project_names_details">
                      <i className="lni lni-user" ></i>
                      <p>
                       <span>{member.username }</span>
                        <span>email@email.com</span>
                      </p>
                  </div>
                  <i className="lni lni-circle-plus invite_to_task"></i>
                </div>
            )}
          </div>
        </div>
      </div>
    </>
  )
}

export default Assign