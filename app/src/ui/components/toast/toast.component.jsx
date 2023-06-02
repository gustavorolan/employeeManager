import React from "react";
import "./toast.component.css";
export const Toast = ({ children, emoji }) => {
  return (
    <div className="toastContainer">
      <div className="toast">
        <p>{emoji}</p>
        <span>{children}</span>
      </div>
    </div>
  );
};
