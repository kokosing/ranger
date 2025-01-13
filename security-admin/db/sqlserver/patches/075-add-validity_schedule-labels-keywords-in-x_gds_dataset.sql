-- Licensed to the Apache Software Foundation (ASF) under one or more
-- contributor license agreements.  See the NOTICE file distributed with
-- this work for additional information regarding copyright ownership.
-- The ASF licenses this file to You under the Apache License, Version 2.0
-- (the "License"); you may not use this file except in compliance with
-- the License.  You may obtain a copy of the License at
--
--     http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.

GO
IF NOT EXISTS(select * from INFORMATION_SCHEMA.columns where table_name = 'x_gds_dataset' and column_name = 'validity_schedule')
BEGIN
	ALTER TABLE [dbo].[x_gds_dataset] ADD [validity_schedule] [nvarchar](max) DEFAULT NULL NULL;
END
GO
GO
IF NOT EXISTS(select * from INFORMATION_SCHEMA.columns where table_name = 'x_gds_dataset' and column_name = 'labels')
BEGIN
	ALTER TABLE [dbo].[x_gds_dataset] ADD [labels] [nvarchar](max) DEFAULT NULL NULL;
END
GO
GO
IF NOT EXISTS(select * from INFORMATION_SCHEMA.columns where table_name = 'x_gds_dataset' and column_name = 'keywords')
BEGIN
	ALTER TABLE [dbo].[x_gds_dataset] ADD [keywords] [nvarchar](max) DEFAULT NULL NULL;
END
GO
exit