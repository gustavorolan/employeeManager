import "./App.css";
import { Toast } from "./ui/components";
import { AllRoutes } from "./ui/components/routes/all-routes";
import { useState } from "react";

function App() {
  const [toast,setToast]= useState(null)
  return (
    <div className="App">   
      <AllRoutes  setToast={setToast}/>
      {toast&&<Toast children={toast.message} emoji={toast.emoji} />}
    </div>
  );
}

export default App;

