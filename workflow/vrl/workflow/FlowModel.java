/*
 * FlowModel.java
 * 
 * Copyright 2012-2013 Michael Hoffer <info@michaelhoffer.de>. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 * 
 * Please cite the following publication(s):
 *
 * M. Hoffer, C.Poliwoda, G.Wittum. Visual Reflection Library -
 * A Framework for Declarative GUI Programming on the Java Platform.
 * Computing and Visualization in Science, 2011, in press.
 *
 * THIS SOFTWARE IS PROVIDED BY Michael Hoffer <info@michaelhoffer.de> "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL Michael Hoffer <info@michaelhoffer.de> OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of Michael Hoffer <info@michaelhoffer.de>.
 */ 

package vrl.workflow;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

/**
 * Defines an interface for 
 * @author Michael Hoffer  &lt;info@michaelhoffer.de&gt;
 */
interface FlowModel extends Model, VisibleState{

    public ConnectionResult tryConnect(VNode s, VNode r, String flowType);
    
    public ConnectionResult connect(VNode s, VNode r, String flowType);

     public ConnectionResult tryConnect(Connector s, Connector r);
    
    public ConnectionResult connect(Connector s, Connector r);
    
    public VNode remove(VNode n);
    
    public void clear();
    
    public ObservableList<VNode> getNodes();
    
    public VNode getSender(Connection c);
    public VNode getReceiver(Connection c);
    
    public void addConnections(Connections connections, String flowType);
    public Connections getConnections(String flowType);
    public ObservableMap<String,Connections> getAllConnections();
    
    public void setFlowNodeClass(Class<? extends VNode> cls);
    
    public Class<? extends VNode> getFlowNodeClass();
    
    public void setIdGenerator(IdGenerator generator);
    public IdGenerator getIdGenerator();
    
    public void setNodeLookup(NodeLookup nodeLookup);
    public NodeLookup getNodeLookup();


}