USE ETM
/*thêm employee*/
CREATE PROC usp_AddEmployee
	@id char(6), @name nvarchar(50), @email nvarchar(50), @phone nvarchar(50), @address nvarchar(50), @skill nvarchar(50), @postion nvarchar(50)
AS
INSERT INTO etm.dbo.Employees(id,name,email,phone,address,skill,position) VALUES(@id,@name,@email,@phone,@address,@skill,@postion)


/*tìm employee*/
CREATE PROC usp_SearchEmployee
	@id char(6), @name nvarchar(50) out, @email nvarchar(50) out, @phone nvarchar(50) out, @address nvarchar(50) out, @skill nvarchar(50) out, @postion nvarchar(50) out
AS
SELECT @name=name, @email=email, @phone=phone, @address=address, @skill=skill,  @postion=position
FROM ETM.dbo.Employees
WHERE id=@id and isDeleted=0 


/*sửa employee*/
CREATE PROC usp_UpdateEmployee
	@id char(6), @name nvarchar(50), @email nvarchar(50), @phone nvarchar(50), @address nvarchar(50), @skill nvarchar(50), @postion nvarchar(50)
AS
UPDATE ETM.dbo.Employees
SET name=@name, email=@email, phone=@phone, address=@address, skill=@skill, position=@postion
WHERE id=@id

/*xóa employee*/
CREATE PROC usp_DeleteEmployee
	@id char(6)
AS
UPDATE ETM.dbo.Employees
SET isDeleted=1
WHERE id=@id

/*tìm project*/
CREATE PROC usp_SearchProject
	@id char(6), @name nvarchar(50) out, @startDate datetime out, @endDate datetime out, @isDeleted bit out
AS
SELECT @name=s.name, @startDate=s.startDate, @endDate=s.endDate, @isDeleted=s.isDeleted
FROM ETM.dbo.Projects s
WHERE id=@id


/*thêm transfer*/
CREATE PROC usp_AddTransfer
	@id char(6), @employeeId nvarchar(50), @fromProjectId char(6), @toProjectId char(6), @status nvarchar(50), @approvedBy nvarchar(50), @startDate datetime, @endDate datetime
AS
INSERT INTO ETM.dbo.Transfers(id,employeeId,fromProjectId,toProjectId,status,approvedBy,startDate,endDate) VALUES(@id,@employeeId,@fromProjectId,@toProjectId,@status,@approvedBy,@startDate,@endDate)

EXEC usp_AddTransfer 'TRF004', 'EMP003', 'PRJ001', 'PRJ002', 'Pending', '', '3/20/2016', '3/30/2016'


SELECT a.id as "ID", a.employeeId as "Employee ID", a.fromProjectId as "From Project ID", a.toProjectId as "To Project ID", a.status as "Status", a.approvedBy as "Approved by" FROM ETM.dbo.Transfers a


/*update transfer*/
CREATE PROC usp_UpdateTransfer
	@id char(6), @employeeId nvarchar(50), @fromProjectId char(6), @toProjectId char(6), @status nvarchar(50), @approvedBy nvarchar(50), @startDate datetime, @endDate datetime
AS
UPDATE ETM.dbo.Transfers
SET employeeId=@employeeId, fromProjectId=@fromProjectId, toProjectId=@toProjectId, status=@status, approvedBy=@approvedBy, startDate=@startDate, endDate = @endDate
WHERE id=@id


/*lấy công việc hiện tại*/
CREATE PROC usp_GetCurrentProject
	@id char(6), @date datetime, @projectId char(6) OUT
AS
SELECT @projectId=b.toProjectId
FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id=b.employeeId
WHERE a.id=@id and (@date BETWEEN b.startDate and b.endDate) and b.status LIKE 'Approved%'


DECLARE @projectId char(6)
EXEC usp_GetCurrentProject 'EMP001', '2017-03-11', @projectId OUT
SELECT @projectId

/*Search Transfer*/
CREATE PROC usp_SearchTransfer
	@id char(6), @employeeId char(6) OUT, @fromProjectId char(6) OUT, @toProjectId char(6) OUT, @status nvarchar(50) OUT, @approvedBy nvarchar(50) OUT, @startDate datetime OUT, @endDate datetime OUT
AS
SELECT @employeeId = employeeId, @fromProjectId = fromProjectId, @toProjectId = toProjectId, @status = status, @approvedBy = approvedBy, @startDate = startDate, @endDate = endDate
FROM ETM.dbo.Transfers
WHERE id = @id



/*Query search employee*/
SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', c.name as 'Current Project', b.startDate as 'Start Date', b.endDate as 'End Date'
FROM ETM.dbo.Employees a FULL OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId FULL OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id
WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.toProjectId = 'EMP001'

SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', c.name as 'Current Project', b.endDate as 'End Date'
FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id
WHERE a.isDeleted='0' and ('' NOT BETWEEN b.startDate AND b.endDate) OR a.id NOT IN (SELECT employeeId FROM ETM.dbo.Transfers)

SELECT id, employeeId, fromProjectId, toProjectId, status, approvedBy, startDate, endDate
FROM ETM.dbo.Transfers
WHERE employeeId = 'EMP001'


SELECT a.id, (SELECT c.name as 'Current Project')
FROM ETM.dbo.Employees a FULL OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId FULL OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id
WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate)


SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', c.name as 'Current Project', b.startDate as 'Start Date', b.endDate as 'End Date'
FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id
WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0'
UNION
SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', NULL as 'Current Project', NULL as 'Start Date', NULL as 'End Date'
FROM ETM.dbo.Employees a FULL OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId FULL OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id
WHERE a.id NOT IN (
	SELECT a.id
	FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id
	WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0'
) AND a.isDeleted='0'


SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', c.name as 'Current Project', b.startDate as 'Start Date', b.endDate as 'End Date'
FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id
WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0' AND a.id NOT IN (
	SELECT a.id
	FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id
	WHERE ('03/07/2016' BETWEEN b.startDate AND b.endDate) AND a.isDeleted='0' AND b.status ='Approved'
)
UNION
SELECT a.id as 'ID', a.name as 'Name', a.position as 'Position', a.skill as 'Skill', NULL as 'Current Project', NULL as 'Start Date', NULL as 'End Date'
FROM ETM.dbo.Employees a FULL OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId FULL OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id
WHERE a.id NOT IN (
	SELECT a.id
	FROM ETM.dbo.Employees a LEFT OUTER JOIN ETM.dbo.Transfers b ON a.id = b.employeeId LEFT OUTER JOIN ETM.dbo.Projects c ON b.toProjectId = c.id
	WHERE ((SELECT GETDATE()) BETWEEN b.startDate AND b.endDate) AND b.status ='Approved' AND a.isDeleted='0'
) AND a.isDeleted='0'