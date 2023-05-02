
package util;
import java.text.SimpleDateFormat;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
import model.Task;
/**
 *
 * @author ton618
 */
public class TaskTableModel extends AbstractTableModel {
    
    String[] columns = {"Nome", "Descrição", "Prazo", "Concluido", "Excluir", "Editar"};
    List<Task> tasks = new ArrayList();
    

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 3;
    }
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        tasks.get(rowIndex).setCompleated((boolean) aValue);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(tasks.isEmpty()) {
            return Object.class;
        }
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Task task = tasks.get(rowIndex);
        switch (columnIndex) {
            case 0 -> {
                return task.getName();
            }
            case 1 -> {
                return task.getDescription();
            }
            case 2 -> {
                SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy");
                return simpleFormat.format(task.getDeadline());
            }
            case 3 -> {
                return task.isCompleated();
            }
            case 4 -> {
                return "";
            }
            case 5 -> {
                return "";
            }
            default -> {
                return "dados não encontrados";
            }
        }
    }

    public String[] getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    
    
}
