import { useState, useEffect } from 'react';

import ReactFlow, {
  ReactFlowProvider,
  Controls,
  Background,
} from 'reactflow';
import 'reactflow/dist/style.css';
import 'semantic-ui-css/semantic.min.css'
import { Container, Button } from 'semantic-ui-react'

import * as React from 'react'


function Flow() {

  const [nodes, setNodes] = useState([]);
  const [edges, setEdges] = useState([]);
    
  const [count, setCount] = useState(0);
  const runDag = () => {
    fetch("http://127.0.0.1:8080/run", {
      method:"GET",
      headers:{
        Accept:"application/json",
        "Content-Type": "application/json"
      }
    })
  }
  useEffect(() => {
    fetch("http://127.0.0.1:8080/dag", {
      method:"GET",
      headers:{
        Accept:"application/json",
        "Content-Type": "application/json"
      }
    }).then((res) => res.json())
      .then((json) => {
        setNodes(json.nodes)
        setEdges(json.edges)
      })
    const timeout = setTimeout(() => {
      setCount(count + 1);
    }, 1000);

    return () => {
      clearTimeout(timeout);
    };
  }, [count]);

  const [windowHeight, setWindowHeight] = useState(0);
  useEffect(() => {
    setWindowHeight(window.innerHeight);

    const handleResize = () => {
      setWindowHeight(window.innerHeight);
    };

    window.addEventListener('resize', handleResize);

    return () => {
      window.removeEventListener('resize', handleResize);
    };
  }, []);
  
  return (
    <>
      <Container fluid style={{height:40}}>
        <Button primary onClick={runDag}>Run</Button>
      </Container>
      <Container fluid style={{height:windowHeight-40}}>
        <ReactFlowProvider>
          <ReactFlow 
            nodes={nodes} 
            edges={edges}
            fitView 
          >
            <Background /> 
            <Controls />
          </ReactFlow>
        </ReactFlowProvider>
      </Container>
    </>
  );
  
}

export default Flow;
