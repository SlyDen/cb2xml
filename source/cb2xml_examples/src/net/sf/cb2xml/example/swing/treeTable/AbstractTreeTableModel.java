/** @(#)JTreeTable.java 1.2 98/10/27** Copyright 1997, 1998 Sun Microsystems, Inc. All Rights Reserved.** Redistribution and use in source and binary forms, with or without* modification, are permitted provided that the following conditions* are met:** - Redistributions of source code must retain the above copyright* notice, this list of conditions and the following disclaimer.** - Redistributions in binary form must reproduce the above copyright* notice, this list of conditions and the following disclaimer in the* documentation and/or other materials provided with the distribution.** - Neither the name of Sun Microsystems nor the names of its* contributors may be used to endorse or promote products derived* from this software without specific prior written permission.** THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS* IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,* THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR* PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR* CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,* EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,* PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR* PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF* LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING* NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.*/package net.sf.cb2xml.example.swing.treeTable;import javax.swing.event.EventListenerList;import javax.swing.event.TreeModelEvent;import javax.swing.event.TreeModelListener;import javax.swing.tree.TreeNode;import javax.swing.tree.TreePath;/** * @version 1.2 10/27/98 * An abstract implementation of the TreeTableModel interface, handling the list * of listeners. * @author Philip Milne update by Bruce Martin for my own projects project */public abstract class AbstractTreeTableModelimplements TreeTableModel {    protected Object root;    protected EventListenerList listenerList = new EventListenerList();    public AbstractTreeTableModel(Object root) {        this.root = root;    }    /**     * Returns the number of children of <code>node</code>.     *     * @see javax.swing.tree.TreeModel#getChildCount(java.lang.Object)     */    public int getChildCount(Object node) {        return ((TreeNode) node).getChildCount();    }    /**     * Returns the child of <code>node</code> at index <code>i</code>.     *     * @see  javax.swing.tree.TreeModel#getChild(java.lang.Object, int)     */    public Object getChild(Object node, int i) {        return ((TreeNode) node).getChildAt(i);    }    /**     * Returns true if the passed in object represents a leaf, false     * otherwise.     *     * @param node to test if it is a leaf node     *     * @return weather it is a leaf node     */    public boolean isLeaf(Object node) {        if (node instanceof TreeNode) {        	return ((TreeNode) node).isLeaf();        }       	return getChildCount(node) == 0;    }    //    // Default implmentations for methods in the TreeModel interface.    //    public Object getRoot() {        return root;    }//    public boolean isLeaf(Object node) {//        return getChildCount(node) == 0;//    }    public void valueForPathChanged(TreePath path, Object newValue) {}    // This is not called in the JTree's default mode: use a naive implementation.    public int getIndexOfChild(Object parent, Object child) {        for (int i = 0; i < getChildCount(parent); i++) {	    if (getChild(parent, i).equals(child)) {	        return i;	    }        }	return -1;    }    /* (non-Javadoc)	 * @see net.sf.RecordEditor.utils.swing.treeTable.TreeTableNotify#addTreeModelListener(javax.swing.event.TreeModelListener)	 */    public void addTreeModelListener(TreeModelListener l) {        listenerList.add(TreeModelListener.class, l);    }    /* (non-Javadoc)	 * @see net.sf.RecordEditor.utils.swing.treeTable.TreeTableNotify#removeTreeModelListener(javax.swing.event.TreeModelListener)	 */    public void removeTreeModelListener(TreeModelListener l) {        listenerList.remove(TreeModelListener.class, l);    }    /*     * Notify all listeners that have registered interest for     * notification on this event type.  The event instance     * is lazily created using the parameters passed into     * the fire method.     * @see EventListenerList     */    /* (non-Javadoc)	 * @see net.sf.RecordEditor.utils.swing.treeTable.TreeTableNotify#fireTreeNodesChanged(java.lang.Object, java.lang.Object[], int[], java.lang.Object[])	 */    public void fireTreeNodesChanged(Object source, Object[] path,                                        int[] childIndices,                                        Object[] children) {        // Guaranteed to return a non-null array        Object[] listeners = listenerList.getListenerList();        TreeModelEvent e = null;        // Process the listeners last to first, notifying        // those that are interested in this event        for (int i = listeners.length-2; i>=0; i-=2) {            if (listeners[i]==TreeModelListener.class) {                // Lazily create the event:                if (e == null)                    e = new TreeModelEvent(source, path,                                           childIndices, children);                ((TreeModelListener)listeners[i+1]).treeNodesChanged(e);            }        }    }    /*     * Notify all listeners that have registered interest for     * notification on this event type.  The event instance     * is lazily created using the parameters passed into     * the fire method.     * @see EventListenerList     */    /* (non-Javadoc)	 * @see net.sf.RecordEditor.utils.swing.treeTable.TreeTableNotify#fireTreeNodesInserted(java.lang.Object, java.lang.Object[], int[], java.lang.Object[])	 */    public void fireTreeNodesInserted(Object source, Object[] path,                                        int[] childIndices,                                        Object[] children) {        // Guaranteed to return a non-null array        Object[] listeners = listenerList.getListenerList();        TreeModelEvent e = null;        // Process the listeners last to first, notifying        // those that are interested in this event        for (int i = listeners.length-2; i>=0; i-=2) {            if (listeners[i]==TreeModelListener.class) {                // Lazily create the event:                if (e == null)                    e = new TreeModelEvent(source, path,                                           childIndices, children);                ((TreeModelListener)listeners[i+1]).treeNodesInserted(e);            }        }    }    /*     * Notify all listeners that have registered interest for     * notification on this event type.  The event instance     * is lazily created using the parameters passed into     * the fire method.     * @see EventListenerList     */    /* (non-Javadoc)	 * @see net.sf.RecordEditor.utils.swing.treeTable.TreeTableNotify#fireTreeNodesRemoved(java.lang.Object, java.lang.Object[], int[], java.lang.Object[])	 */    public void fireTreeNodesRemoved(Object source, Object[] path,                                        int[] childIndices,                                        Object[] children) {        // Guaranteed to return a non-null array        Object[] listeners = listenerList.getListenerList();        TreeModelEvent e = null;        // Process the listeners last to first, notifying        // those that are interested in this event        for (int i = listeners.length-2; i>=0; i-=2) {            if (listeners[i]==TreeModelListener.class) {                // Lazily create the event:                if (e == null)                    e = new TreeModelEvent(source, path,                                           childIndices, children);                try {                	((TreeModelListener)listeners[i+1]).treeNodesRemoved(e);                } catch (Exception ex) { 				}            }        }   }    /*     * Notify all listeners that have registered interest for     * notification on this event type.  The event instance     * is lazily created using the parameters passed into     * the fire method.     * @see EventListenerList     */    /* (non-Javadoc)	 * @see net.sf.RecordEditor.utils.swing.treeTable.TreeTableNotify#fireTreeStructureChanged(java.lang.Object, java.lang.Object[], int[], java.lang.Object[])	 */    public void fireTreeStructureChanged(Object source, Object[] path,                                        int[] childIndices,                                        Object[] children) {        // Guaranteed to return a non-null array        Object[] listeners = listenerList.getListenerList();        TreeModelEvent e = null;        // Process the listeners last to first, notifying        // those that are interested in this event        for (int i = listeners.length-2; i>=0; i-=2) {            if (listeners[i]==TreeModelListener.class) {                // Lazily create the event:                if (e == null) {                    e = new TreeModelEvent(source, path,                                           childIndices, children);                }                ((TreeModelListener)listeners[i+1]).treeStructureChanged(e);            }        }    }    //    // Default impelmentations for methods in the TreeTableModel interface.    //	@SuppressWarnings("rawtypes")	public Class getColumnClass(int column) { return Object.class; }   /** By default, make the column with the Tree in it the only editable one.    *  Making this column editable causes the JTable to forward mouse    *  and keyboard events in the Tree column to the underlying JTree.    */    public boolean isCellEditable(Object node, int column) {         return getColumnClass(column) == TreeTableModel.class;    }    public void setValueAt(Object aValue, Object node, int column) {}    // Left to be implemented in the subclass:    /*     *   public Object getChild(Object parent, int index)     *   public int getChildCount(Object parent)     *   public int getColumnCount()     *   public String getColumnName(Object node, int column)     *   public Object getValueAt(Object node, int column)     */}