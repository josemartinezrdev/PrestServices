import React, { useState, useEffect } from "react";
import Llamados from "./Llamados";
import "./CrudTable.css";

const CrudTable = ({ tableName, data = [], onDataUpdate }) => {
  const [items, setItems] = useState(data);
  const [newItem, setNewItem] = useState({});
  const [editingItem, setEditingItem] = useState(null); // Modo edición
  const [showTable, setShowTable] = useState(false);
  const userToken = localStorage.getItem("userToken");

  useEffect(() => {
    setItems(data);
  }, [data]);

  const convertToPlural = (singular) => {
    console.log(singular);
    if (singular.endsWith("s")) {
      return singular;
    } else if (singular.endsWith("z")) {
      return singular.replace(/z$/, "ces");
    } else if (singular.endsWith("ion")) {
      return singular + "es";
    } else if (singular.endsWith("n")) {
      return singular + "es";
    } else if (singular.endsWith("d")) {
      return singular + "es";
    } else if (singular.endsWith("r")) {
      console.log(singular);
      return singular + "s";
    } else {
      return singular + "s";
    }
  };
  console.log(data);
  const fields =
    data && data.length > 0
      ? Object.keys(data[0]).filter(
          (key) => key !== "id" && key !== "nrodoc" && key !== "norden"
        )
      : [];

  const handleInputChange = (e, isEditing = false, parentKey = null) => {
    const { name, value } = e.target;

    if (parentKey) {
      if (isEditing) {
        setEditingItem((prev) => ({
          ...prev,
          [parentKey]: {
            ...prev[parentKey],
            [name]: value,
          },
        }));
      } else {
        setNewItem((prev) => ({
          ...prev,
          [parentKey]: {
            ...prev[parentKey],
            [name]: value,
          },
        }));
      }
    } else {
      if (isEditing) {
        setEditingItem((prev) => ({ ...prev, [name]: value }));
      } else {
        setNewItem((prev) => ({ ...prev, [name]: value }));
      }
    }
  };

  const handleSelectIdChange = (key, selectedId, isEditing = false) => {
    if (isEditing) {
      setEditingItem((prev) => ({
        ...prev,
        [key]: { id: selectedId },
      }));
    } else {
      setNewItem((prev) => ({
        ...prev,
        [key]: { id: selectedId },
      }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const itemWithoutId = {};
    fields.forEach((field) => {
      if (typeof data[0][field] === "object" && data[0][field] !== null) {
        itemWithoutId[field] = newItem[field] || {};
      } else {
        itemWithoutId[field] = newItem[field] || "";
      }
    });

    try {
      const response = await fetch(`/api/${tableName}`, {
        method: "POST",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(itemWithoutId),
      });

      if (!response.ok) {
        throw new Error("Error al agregar el item: " + response.statusText);
      }

      const addedItem = await response.json();
      const updatedItems = [...items, addedItem];
      setItems(updatedItems);
      onDataUpdate(updatedItems);
      setNewItem({});
    } catch (error) {
      console.error("Error al agregar el item:", error);
      alert("Error al agregar el item");
    }
  };

  const handleUpdate = async () => {
    const itemWithoutId = {};

    fields.forEach((field) => {
      if (typeof data[0][field] === "object" && data[0][field] !== null) {
        itemWithoutId[field] = editingItem[field] || {};
      } else {
        itemWithoutId[field] = editingItem[field] || "";
      }
    });

    try {
      console.log(tableName);
      const response = await fetch(`/api/${tableName}/${editingItem.id}`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(itemWithoutId),
      });

      if (!response.ok) {
        throw new Error("Error al actualizar el item: " + response.statusText);
      }

      const updatedItems = items.map((item) =>
        item.id === editingItem.id ? editingItem : item
      );
      setItems(updatedItems);
      onDataUpdate(updatedItems);
      setEditingItem(null);
    } catch (error) {
      console.error("Error al actualizar el item:", error);
      alert("Error al actualizar el item");
    }
  };

  const handleEdit = (item) => {
    setEditingItem(item);
  };

  const handleDelete = async (id) => {
    const updatedItems = items.filter((item) => item.id !== id);
    setItems(updatedItems);
    onDataUpdate(updatedItems);

    try {
      await fetch(`/api/${tableName}/${id}`, {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${userToken}`,
          "Content-Type": "application/json",
        },
      });
    } catch (error) {
      console.error("Error al eliminar el item:", error);
      alert("Error al eliminar el item");
    }
  };

  const handleToggleTable = () => {
    setShowTable((prevShow) => !prevShow);
  };

  return (
    <div className="crud-table-container">
      <h2>{tableName}</h2>

      <form onSubmit={handleSubmit} className="form-grid">
        {fields.map((field) => (
          <React.Fragment key={field}>
            {typeof data[0][field] === "object" && data[0][field] !== null ? (
              <Llamados
                pluralkey={convertToPlural(field)}
                onSelectId={(selectedId) =>
                  handleSelectIdChange(field, selectedId)
                }
              />
            ) : (
              <input
                type="text"
                name={field}
                value={newItem[field] || ""}
                onChange={handleInputChange}
                placeholder={field}
              />
            )}
          </React.Fragment>
        ))}
        <button type="submit">Add Item</button>
      </form>

      <button onClick={handleToggleTable}>
        {showTable ? "Hide Items" : "List Items"}
      </button>

      {showTable && (
        <table>
          <thead>
            <tr>
              {fields.map((field) => (
                <th key={field}>{field}</th>
              ))}
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {items.map((item) => (
              <tr key={item.id}>
                {fields.map((field) => (
                  <td key={field}>
                    {editingItem && editingItem.id === item.id ? (
                      <div className="edit-form-grid">
                        {typeof item[field] === "object" &&
                        item[field] !== null ? (
                          <Llamados
                            pluralkey={convertToPlural(field)}
                            onSelectId={(selectedId) =>
                              handleSelectIdChange(field, selectedId, true)
                            }
                          />
                        ) : (
                          <input
                            type="text"
                            name={field}
                            value={editingItem[field] || ""}
                            onChange={(e) => handleInputChange(e, true)}
                          />
                        )}
                      </div>
                    ) : typeof item[field] === "object" &&
                      item[field] !== null ? (
                      Object.keys(item[field])
                        .map((key) => item[field][key])
                        .join(", ")
                    ) : (
                      item[field]
                    )}
                  </td>
                ))}
                <td className="contenedor-botones">
                  {editingItem && editingItem.id === item.id ? (
                    <button onClick={handleUpdate}>Save</button>
                  ) : (
                    <button onClick={() => handleEdit(item)}>Edit</button>
                  )}
                  <button onClick={() => handleDelete(item.id)}>Delete</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default CrudTable;
